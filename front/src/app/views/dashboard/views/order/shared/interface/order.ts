export interface Order extends OrderForm {
  id: string;
}

export interface OrderForm{
    
     company: {
    id: string | null;
    name: string;
  };
    orderDate: string,
    orderStatus : string,
    orders : string,
    totalAmount: string,

}