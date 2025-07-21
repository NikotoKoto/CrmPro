import { OrderService } from './../shared/service/order.service';
import { Component, inject, input, output } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { OrderForm } from '../shared/interface/order';

@Component({
  selector: 'app-order-form',
  imports: [ReactiveFormsModule],
  template: `
    <form [formGroup]="orderForm" (ngSubmit)="submit()">
      <div class="flex flex-col">
        <div class="mb-20 flex flex-col">
          <label for="company">Nom de l'entreprise</label>
          <input formControlName="company" type="text" id="company" />
        </div>
        <div class="mb-20 flex flex-col">
          <label for="orderDate">Date</label>
          <input formControlName="orderDate" type="date" id="orderDate" />
        </div>
        <div class="mb-20 flex flex-col">
          <label for="orderStatus">Status</label>
          <select formControlName="orderStatus" type="range" id="orderStatus">
            <option value="PENDING">PENDING</option>
            <option value="SHIPPED">SHIPPED  </option>
            <option value="CANCELED">CANCELED </option>
            <option value="DELIVERED">DELIVERED  </option>
            <option value="CANCELED">CANCELED  </option>
   
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
        <button class="btn btn-primary">Ajouter</button>
      </div>
    </form>
  `,
  styles: ``,
})
export class OrderFormComponent {
  private fb = inject(FormBuilder);
  private orderService = inject(OrderService);
  formSubmitted = output<void>();
  
  orderForm = this.fb.group({
    company: ['', Validators.required],
    orderDate: ['', [Validators.required, Validators.email]],
    orderStatus: ['pending'],
    orders:['',Validators.required],
    totalAmount: ['', Validators.required],
  });

  submit() {
    const orderForm: OrderForm = this.orderForm.getRawValue() as OrderForm;
    this.orderService.addOrder(orderForm);
    this.formSubmitted.emit();
  }
}
