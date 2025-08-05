import { OrderService } from './shared/service/order.service';
import { Component, inject, input, signal } from '@angular/core';
import { OrdersListComponent } from "./components/orders-list.component";
import { Order, OrderForm } from './shared/interface/order';
import { OrderFormComponent } from "./components/order-form.component";

@Component({
  selector: 'app-order',
  imports: [OrdersListComponent, OrderFormComponent],
  template: `
   
      <h2 class="text-xxl text-bold  my-10">📦 Les commandes </h2>
      <button (click)="onAdd()"> Add</button>
      @if(showForm()){
        <app-order-form
        [order]="currentOrderEditing()"
        (formSubmitted)="onFormSubmitted($event)"/>
      }
      <app-orders-list (isEdit)="onEditClick($event)" (isDelete)="onDelete($event)"/>
  `,
  styles: `
  :host{
    display:flex;
    flex-direction:column;
    align-items:center;
    gap: 50px;

    .order-grid{
     display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 1rem;
  }

  }`
})
export class OrderComponent {
  private orderService = inject(OrderService);
  showForm = signal(false);
  currentOrderEditing = this.orderService.currentEditingOrder;

  onEditClick(orderToEdit: Order) {  
  this.currentOrderEditing.set(orderToEdit); 
  this.showForm.set(true);            
}

onAdd(){
  this.currentOrderEditing.set(null);
  this.showForm.set(true);
}

 onDelete(id: string){
    this.orderService.deleteOrder(id)
  }

  onFormSubmitted(order: Order | OrderForm) {
    console.log("trigger:" , order)
    if('id' in order){
      console.log("Mod Edit")
      this.orderService.editOrder(order)
    }else{
      console.log("ModAdd")
      this.orderService.addOrder(order)
    }
    this.showForm.set(false);
    this.currentOrderEditing.set(null);
  }
}
