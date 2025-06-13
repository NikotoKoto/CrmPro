
import { Component, signal } from '@angular/core';
import { AuthPageComponent } from '../authPage/auth-page.component';
@Component({
  selector: 'app-landing-page',
  imports: [AuthPageComponent],
  template: `
    <div class="discoverySection ">
      <div class="flex flex-col justify-center items-center gap-16">
        <h1 class="text-xxl title">Gérez vos clients efficacement</h1>
        <h3>
          Une solution simple et puissante pour suivre vos contacts, ventes et
          tâches
        </h3>
        <button class="mt-20 btn btn-primary" (click)="scrollToForm()">Commencez gratuitement</button>
      </div>
    </div>

    <div class="landing-section">
      <div>
        <ul class="features-list">
        @for(feature of features(); track $index){
           <li>
            <span>{{feature.icon}}</span>
            <h4>{{feature.title}}</h4>
            <p>{{feature.description}}</p>
          </li>
        }  
       
         
        </ul>
      </div>
      <app-auth-page id="signup-section"/>
    </div>
    <div class="img-container">
      <img src="assets/screenshot.png" alt="screenshot" />
    </div>
  `,
  styles: `
  :host{
    display:flex;
    flex-direction:column;
    align-items:center;

  }
  
  .discoverySection{
    width:100%;
    background-color:var(--section-bg);
    padding: var(--spacing-xl) var(--spacing-md);
    text-align:center;
  }
  .title{
    max-width: 600px; 
  line-height: 1.2;
  word-break: break-word;
  text-align: center;
  }
  .landing-section{
    width:100%;
    background-color: var(--background-color);
    padding: 0 var(--spacing-md) var(--spacing-xl) 0;
    display:grid;
    grid-template-columns: 1fr 1fr;
    justify-content: center;

  }
  .features-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: var(--spacing-lg);
  padding: var(--spacing-md);

}
.features-list li {
  display: flex;
  flex-direction:column;
  align-items: center;
  font-size: var(--font-size-lg);
  gap: var(--spacing-sm);
  padding: var(--spacing-md) var(--spacing-md);
}
.features-list li span {
  font-size: 2.5rem;
}

.features-list li h4 {
  margin: 0;
  font-size: var(--font-size-lg);
  color: var(--primary);
}

.features-list li p {
  margin: 0;
  text-align:justify;
  font-size: var(--font-size-sm);
  color: var(--text-muted);
}


  .img-container {
  width: 100%;
  display: flex;
  justify-content: center;
  background-color: var(--section-bg);
  padding: var(--spacing-md) var(--spacing-md);
  padding-bottom: 0;
  margin-bottom: 0;
}
  .img-container img {
  max-width: 100%;
  height: auto;
  border-radius: var(--radius);
  box-shadow: var(--shadow-sm);
  display: block;
}
`,
})
export class LandingPageComponent {

  features = signal([
  {
    icon: '👥',
    title: 'Gestion des clients',
    description: 'Centralisez les informations de vos clients'
  },
  {
    icon: '💬',
    title: 'Suivi des interactions',
    description: 'Consignez chaque échange avec vos clients'
  },
  {
    icon: '📊',
    title: 'Statistiques',
    description: 'Analyser vos performances commerciales'
  },
  {
    icon: '⏰',
    title: 'Rappels & alertes',
    description: 'Ne manquez jamais une opportunité'
  },
  {
    icon: '📝',
    title: 'Gestion des tâches',
    description: 'Créez, assignez et suivez les tâches de votre équipe'
  },
  {
    icon: '📅',
    title: 'Historique des activités',
    description: 'Visualisez l’ensemble des actions liées à chaque client'
  }]
  );

  scrollToForm() {
    const el = document.getElementById('signup-section');
    if (el) {
      el.scrollIntoView({ behavior: 'smooth' });
    }
  }
}
