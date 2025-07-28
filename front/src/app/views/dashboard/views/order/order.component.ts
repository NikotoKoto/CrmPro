import { Component, input, signal } from '@angular/core';
import { OrdersListComponent } from "./components/orders-list.component";
import { Order } from './shared/interface/order';

@Component({
  selector: 'app-order',
  imports: [OrdersListComponent],
  template: `
   
      <h2 class="text-xxl text-bold  my-10">📦 Les commandes </h2>
      <button (click)="onAdd()"> Add</button>
      @if(showForm()){
        <app-order-form
        [order]="editingOrder()"
        (formSubmitted)="onFormSubmitted()"/>
      }
      <app-orders-list (isEdit)="isEdit($event)" (isDelete)="isDelete($event)"/>
  `,
  styles: `
  :host{
    display:flex;
    flex-direction:column;
    align-items:center;
    gap: 50px;

  }`
})
export class OrderComponent {
showForm = signal(false);
editingOrder = signal<Order | null> (null);

onFormSubmitted() {
    this.showForm.set(false);
    this.editingOrder.set(null);
  }

onAdd(){
  this.editingOrder.set(null);
  this.showForm.set(true);
}

 isDelete(id: string){
    this.OrderService.deleteOrder(id)
  }

  isEdit(updateOrder: Order){
    this.OrderService.editOrder(updateOrder)
  }
}
