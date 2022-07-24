import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  credentials = {username: '', password: '', email: ''};

  constructor(private app: AppService, private http: HttpClient, private router: Router) {}

  register() {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');

    this.http.post<any>('http://localhost:8080/register', JSON.stringify(this.credentials), {headers: headers}).subscribe(response => {
      if (response['id' as keyof typeof response && 'email' as keyof typeof response]) {
        this.router.navigateByUrl('/');
      }
      return false;
    });
  }
}
