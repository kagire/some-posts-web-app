import {Component} from '@angular/core';
import { AppService } from './app.service';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {FullPost} from "./fullpost";

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  public posts : FullPost[] = [];

  constructor(public app: AppService, private http: HttpClient) {
    if(this.authenticated()){
      this.app.loadPosts().subscribe((response : FullPost[]) => {
        this.posts = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      })
    }
    else this.posts = [];
  }

  authenticated() { return this.app.authenticated; }

}
