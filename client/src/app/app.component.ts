import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./shares/header/header.component";
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'microservices-shop-frontend';
private readonly oidSecurityService = inject(OidcSecurityService);

  ngOnInit(): void {
    this.oidSecurityService.checkAuth().subscribe(({isAuthenticated}) =>{
      console.log('app authenticated', isAuthenticated);

    })
  }
}
