import { Order, OrderForm } from './../interface/order';
import { computed, Injectable, resource, signal } from '@angular/core';

const API_ORDER = 'http://localhost:8080/orders';
@Injectable({
  providedIn: 'root'
})
export class OrderService {
currentOrderResource = resource({
  loader:()=> this.fetchCurrentOrder()
})

currentOrder = computed(()=> this.currentOrderResource.value())

  async addOrder(orderForm : OrderForm){
    try{
const response = await fetch(`${API_ORDER}`,{
  method: "POST",
  headers:{
    "Content-type" : "application/json"
  },
  body: JSON.stringify(orderForm),
  credentials: 'include'
})
if(response.ok){
  const data = await response.json();
  this.currentOrderResource.reload();
  return data;
}else{
  throw new Error("Oops")
}
    }catch{
      throw new Error("Oops")
    }
  }



  async fetchCurrentOrder(){
   const response = await fetch(`${API_ORDER}`,{
    credentials:'include'
   })
   if(!response.ok){
    return undefined;
   }
   return await response.json();
  }
  constructor() { }
}
