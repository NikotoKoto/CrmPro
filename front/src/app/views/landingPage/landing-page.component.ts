
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
      <h3 class="text-landing text-lg">Curieux? Essayez-nous gratuitement en vous inscrivant</h3>
      <div class="landing-grid">
        <ul class="features-list">
        @for(feature of features(); track $index){
           <li>
            <span>{{feature.icon}}</span>
            <h4>{{feature.title}}</h4>
            <p>{{feature.description}}</p>
          </li>
        }    
        </ul>
        <app-auth-page id="signup-section"/>
      </div>
    </div>
    <section class="dashboard-info">
  <h2>Découvrez le Dashboard (Projet de Portfolio)</h2>
<p>
  Ce projet est conçu comme une démonstration de mes compétences en développement web full-stack. 
  Il s'agit d'une application CRM simplifiée, développée dans le cadre de mon portfolio. 
  Certaines fonctionnalités peuvent être incomplètes ou en cours d'amélioration.
</p>

<p>
  Pour accéder au tableau de bord, vous devez d'abord vous <strong>inscrire</strong> ou vous <strong>connecter</strong>. 
  Une fois authentifié, vous pourrez visualiser et manipuler vos données via les APIs sécurisées.
</p>

<div class="cards">
  <div class="card">
    <h3>Gestion des clients</h3>
    <p>
      Ajoutez, modifiez ou supprimez vos clients grâce à l'API REST 
      <code>/api/contacts</code>. Chaque opération est associée à votre compte utilisateur.
    </p>
  </div>

  <div class="card">
    <h3>Gestion des commandes</h3>
    <p>
      Créez et suivez vos commandes, et liez-les à vos clients via l'API 
      <code>/api/orders</code>. Seul l'utilisateur propriétaire peut voir ou modifier ses données.
    </p>
  </div>

  <div class="card">
    <h3>Dashboard sécurisé</h3>
    <p>
      Accédez à une vue synthétique de vos clients et de vos commandes dans un tableau de bord 
      protégé, accessible uniquement après authentification via JWT.
    </p>
  </div>
</div>
</section>

  `,
  styles: `
  :host {
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* Section découverte */
.discoverySection {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to bottom, white 0%, #f9f9f9 30%, var(--section-bg) 100%);
  padding: var(--spacing-xl) var(--spacing-md);
  text-align: center;
  height: auto;
  min-height: 600px;
}

.title {
    max-width: 600px;
  line-height: 1.2;
  word-break: break-word;
  text-align: center;
}

/* Section centrale */
.landing-section {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: var(--spacing-md);
  width: 100%;
}
.text-landing{
  text-align:center;
}

.landing-grid {
  width: 100%;
  background-color: var(--background-color);
  padding: var(--spacing-md);
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--spacing-md);
  max-width: 1200px;
}

/* Mobile & tablette */
@media (max-width: 767px) {
  .landing-grid {
    grid-template-columns: 1fr;
  }

  .features-list {
    grid-template-columns: 1fr;
  }

  .features-list li {
    text-align: center;
  }
}

.features-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: var(--spacing-lg);
  padding: var(--spacing-md);
}

.features-list li {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: var(--font-size-lg);
  gap: var(--spacing-sm);
  padding: var(--spacing-md);
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
  text-align: justify;
  font-size: var(--font-size-sm);
  color: var(--text-muted);
}

/* Section dashboard */
.dashboard-info {
  padding: 3rem 1rem;
  text-align: center;
  width: 100%;
  background-color: #f9fafb;
}

.dashboard-info h2 {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 1rem;
}

.dashboard-info p {
  font-size: 1.1rem;
  max-width: 700px;
  margin: 0 auto 2rem auto;
}

.cards {
  display: grid;
  gap: 1.5rem;
  max-width: 1000px;
  margin: 0 auto;
}

/* Responsive cards */
@media (max-width: 767px) {
  .cards {
    grid-template-columns: 1fr;
  }
}

@media (min-width: 768px) and (max-width: 1024px) {
  .cards {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (min-width: 1025px) {
  .cards {
    grid-template-columns: repeat(3, 1fr);
  }
}

.card {
  background: #fff;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease-in-out;
}

.card:hover {
  transform: scale(1.05);
}

.card h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 0.5rem;
}

.card p {
  font-size: 0.95rem;
  line-height: 1.4;
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
