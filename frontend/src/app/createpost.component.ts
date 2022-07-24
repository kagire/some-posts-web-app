import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Router } from '@angular/router';
import {FullPost} from "./fullpost";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  templateUrl: './createpost.component.html'
})
export class CreatePostComponent {
  formData: FormGroup;
  files: File[] = [];
  post = {title: '', text: ''};

  constructor(private formBuilder: FormBuilder, private app: AppService, private http: HttpClient, private router: Router) {
    this.formData = this.formBuilder.group({
      files: []
    });
  }

  create() {
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', this.app.authToken);
    const formData: any = new FormData();
    for (var i = 0; i < this.files.length; i++) {
      formData.append('images', this.files[i]);
    }
    formData.append('title', this.post.title);
    formData.append('text', this.post.text);

    this.http.post<HttpResponse<any>>('http://localhost:8080/posts/create', formData, {headers: headers}).subscribe(response => {
      if (response.status == 201) {
        this.router.navigateByUrl('/');
      }
    });
  }

  onFileChange(event: any){
    this.files = event.target.files;
  }
}
