import { OrderService } from './../shared/service/order.service';
import { Component, effect, inject, input, output } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { Order, OrderForm } from '../shared/interface/order';

@Component({
  selector: 'app-order-form',
  imports: [ReactiveFormsModule],
  template: `
    <form [formGroup]="orderForm" (ngSubmit)="submit()">
      <div class="flex flex-col">
        <div class="mb-20 flex flex-col" formGroupName="company">
          <label for="company">Nom de l'entreprise</label>
          <input formControlName="name" type="text" id="company" />
        </div>
        <div class="mb-20 flex flex-col">
          <label for="orderDate">Date</label>
          <input formControlName="orderDate" type="date" id="orderDate" />
        </div>
        <div class="mb-20 flex flex-col">
          <label for="orderStatus">Status</label>
          <select formControlName="orderStatus" type="range" id="orderStatus">
            <option value="PENDING">PENDING</option>
            <option value="SHIPPED">SHIPPED</option>
            <option value="CANCELED">CANCELED</option>
            <option value="DELIVERED">DELIVERED</option>
          </select>
        </div>
        <div class="mb-20 flex flex-col">
          <label for="orders">Commande</label>
          <input formControlName="orders" type="text" id="orders" />
        </div>
        <div class="mb-20 flex flex-col">
          <label for="totalAmount">Montant</label>
          <input formControlName="totalAmount" type="number" id="totalAmount" />
        </div>
        @if(order()){
          <button class="btn btn-primary">Modifier</button>
        }@else {

          <button class="btn btn-primary">Ajouter</button>
        }
      </div>
    </form>
  `,
  styles: ``,
})
export class OrderFormComponent {
  private fb = inject(FormBuilder);
  formSubmitted = output<OrderForm | Order>();
  order = input<Order | null>(null)

  orderForm = this.fb.group({
    company: this.fb.group({
      id: [''],
      name: ['', Validators.required],
    }),
    orderDate: ['', [Validators.required]],
    orderStatus: ['PENDING',[Validators.required]],
    orders: ['', Validators.required],
    totalAmount: ['', Validators.required],
  });


    initOrderEffect = effect(() => {
      const currentOrder = this.order();
      console.log(currentOrder)
      if (currentOrder) {
       this.orderForm.patchValue({
      company: {
        id: currentOrder.company.id,
         name: typeof currentOrder.company === 'string' ? currentOrder.company : currentOrder.company.name,
      },
      orderDate: currentOrder.orderDate,
      orderStatus: currentOrder.orderStatus,
      orders: currentOrder.orders,
      totalAmount: currentOrder.totalAmount,
    });
      } else {
        
        this.orderForm.reset({
          company: { id: '', name: '' },
          orderDate: '',
          orderStatus: 'PENDING',
          orders: '',
          totalAmount: "0",
        });
      }
    });
  

    submit() {
    const orderForm: OrderForm = this.orderForm.getRawValue() as OrderForm;
    if(this.order()){
      const updateOrder: Order = {
        ...orderForm,
        id: this.order()!.id
      }
      this.formSubmitted.emit(updateOrder)
      console.log(updateOrder)
    }else{

      this.formSubmitted.emit(orderForm);
    }
  }
}
