import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  imports: [],
  template: `
    
      <p class="text-lg">
        "Votre relation Client, simplifiée."
      </p>
      <p> CRM copyright © 2025-2026</p>
   
  `,
  styles: `
  :host{
    display:flex;
    flex-direction:column;
    align-items: center;
    justify-content:center;
    padding-top:0;
    width: 100%;
    min-height: 155px;
    background-color: var(--grey);
    color: var(--white);
  }`
})
export class FooterComponent {

}
