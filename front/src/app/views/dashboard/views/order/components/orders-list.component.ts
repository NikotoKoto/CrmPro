import { Component, inject, output, signal } from '@angular/core';
import { OrderService } from '../shared/service/order.service';
import { OrderFormComponent } from './order-form.component';
import { Order } from '../shared/interface/order';

@Component({
  selector: 'app-orders-list',
  imports: [OrderFormComponent],
  template: `
     <div class="order-grid">
      @for(order of orderList(); track $index){
        <div class="card">
          <button class="deleteButton" (click)="isDelete.emit(order.id)">❌</button>
          <button class="editButton" (click)="isEdit.emit(order)">✏️</button>
          <ul>
            <li class="order-detail"><strong>Entreprise:</strong> {{ order.company }}</li>
            <li class="order-detail"><strong>Date:</strong> {{ order.orderDate }}</li>
            <li class="order-detail"><strong>Status:</strong> {{ order.orderStatus }}</li>
            <li class="order-detail"><strong>Commande:</strong> {{ order.orders }}</li>
            <li class="order-detail"><strong>Total:</strong> {{ order.totalAmount }} €</li>
          </ul>
        </div>
      }
    </div>
    
  `,
  styles: `
  :host{
    display:block;
    padding:1rem;
    width:100%;
    
  }

  .order-form{
    width:200px;
  }

  .btn-add{
    font-size:50px;
    width:200px;
    height: 350px;
  }
  .card{
    position:relative;
    width:200px;
    height:350px;
  }

  .editButton{
    position:absolute;
    left:10px;
    top:3px;
    background:none;
    border: none;
    cursor:pointer;

     &:active{
      transform:scale(0.95);
    }
  }

  .deleteButton{
    position:absolute;
    right:10px;
    top:3px;
    background:none;
    border: none;
    cursor:pointer;

    

    &:active{
      transform:scale(0.95);
    }
  }

  .order-grid{
     display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 1rem;
  }

  .order-detail {
    display:flex;
    flex-direction:column;
  padding: 0.5rem;
  border-bottom: 1px solid #eee;
  font-size: 1.2rem;
  color: #333;
}
 `,
})
export class OrdersListComponent {
  private OrderService = inject(OrderService);
  orderList = this.OrderService.currentOrder;
  isCollapse = signal(false);
  editingOrderId = signal<string | null>(null);
  isEdit = output<Order>();
  isDelete= output<string>();
  

 

 
}
