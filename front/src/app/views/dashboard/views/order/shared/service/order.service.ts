import { AuthService } from '../../../../../../shared/services/auth.service';
import { Order, OrderForm } from './../interface/order';
import { computed, effect, inject, Injectable, resource, signal } from '@angular/core';

const API_ORDER = 'http://localhost:8080/orders';
@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private auth = inject(AuthService);
  currentOrderResource = resource({
    loader: () => this.fetchCurrentOrder(),
  });

  currentOrder = computed(() => this.currentOrderResource.value());

  currentEditingOrder = signal<Order | null>(null);

  async addOrder(orderForm: OrderForm) {
    try {
      const response = await fetch(`${API_ORDER}`, {
        method: 'POST',
        cache: 'no-store',
        headers: {
          'Content-type': 'application/json',
           'Cache-Control': 'no-cache'
        },
        body: JSON.stringify(orderForm),
        credentials: 'include',
      });
      if (response.ok) {
        const data = await response.json();
        this.currentOrderResource.reload();
        return data;
      } else {
        throw new Error('Oops');
      }
    } catch {
      throw new Error('Oops');
    }
  }

  async fetchCurrentOrder(): Promise<Order[] | undefined> {
    const response = await fetch(`${API_ORDER}`, {
      credentials: 'include',
      cache: 'no-store',
      headers: {
        'Cache-Control': 'no-cache',
      },
    });
    if (!response.ok) {
      return undefined;
    }
    return await response.json();
  }

  async editOrder(order: Order) {
    try {
      const { id, ...RestOrder } = order;
      const response = await fetch(`${API_ORDER}/${id}`, {
        method: 'PATCH',
        headers: {
          'Content-type': 'application/json',
          'Cache-Control': 'no-cache',
        },
        body: JSON.stringify(RestOrder),
        credentials: 'include',
      });
      const data = await response.json();
      if (response.ok) {
        this.currentOrderResource.reload();
        return data;
      } else {
        throw new Error('Oops');
      }
    } catch {
      throw new Error('Oops');
    }
  }

  async deleteOrder(orderId: string) {
    await fetch(`${API_ORDER}/${orderId}`, {
      method: 'DELETE',
      credentials: 'include',
      cache: 'no-store',
      headers: {
        'Cache-Control': 'no-cache',
      },
    });
    this.currentOrderResource.reload();
  }
  constructor() {
    effect(() => {
    this.auth.authChanged();       // se re-déclenche après login/register/logout
    this.currentOrderResource.reload();  // ⬅️ recharge la liste automatiquement
  });
  }
}
