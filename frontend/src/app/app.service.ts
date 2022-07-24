import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {FullPost} from "./fullpost";
import {Observable} from "rxjs";

@Injectable()
export class AppService {

  postList : FullPost[] = [];
  authenticated = false;
  authenticatedEmail = "";
  authToken = "";

  constructor(private http: HttpClient) {}

  authenticate(credentials: any, callback: any) {

    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');

    this.http.post<any>('http://localhost:8080/login', JSON.stringify(credentials), {headers: headers}).subscribe(response => {
      if (response['email' as keyof typeof response] && response["token" as keyof typeof response]) {
        this.authToken = response.token;
        this.authenticatedEmail = response.email;
        this.authenticated = true;
      } else {
        this.authenticatedEmail = "";
        this.authToken = "";
        this.authenticated = false;
      }
      return callback && callback();
    });
  }

  loadPosts() : Observable<FullPost[]>{
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json; charset=utf-8').set('Authorization', this.authToken);
    return this.http.get<FullPost[]>('http://localhost:8080/posts', {headers: headers});
  }
}
