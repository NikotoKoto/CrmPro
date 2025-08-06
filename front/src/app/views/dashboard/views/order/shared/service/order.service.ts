import { environment } from '../../../../../../../environments/environment';
import { Order, OrderForm } from './../interface/order';
import { computed, Injectable, resource, signal } from '@angular/core';

const API_ORDER = `${environment.apiUrl}/orders`;
@Injectable({
  providedIn: 'root'
})
export class OrderService {
currentOrderResource = resource({
  loader:()=> this.fetchCurrentOrder()
})

currentOrder = computed(()=> this.currentOrderResource.value())
currentEditingOrder = signal<Order | null> (null);
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

  async editOrder(order : Order){
    try{
      const {id, ...RestOrder} = order;
      const response = await fetch(`${API_ORDER}/${id}`,{
        method: "PATCH",
        headers: {
          "Content-type" : "application/json"
        },
        body: JSON.stringify(RestOrder),
        credentials: 'include'
              }
      )
       const data = await response.json();
       if(response.ok){
         this.currentOrderResource.reload()
        return data;
       }else{
        throw new Error("Oops")
       }

    }catch{
      throw new Error("Oops");
    }
  }

  async deleteOrder(orderId: string){
    await fetch(`${API_ORDER}/${orderId}`,{
      method : 'DELETE',
      credentials : 'include'
    }
      )
  this.currentOrderResource.reload();
  }
  constructor() { }
}
