export interface ContactForm {
    name:string,
    email:string,
    phone:string,
     company: {
    id: string | null;
    name: string;
  }; 
}

export interface Contact extends ContactForm {
    id: string,
    
}
