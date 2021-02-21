import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { results } from './results';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  apiUrl= 'http://localhost:8983/solr/wiki/select?q=*%3A*';

  constructor(private http: HttpClient) { 
    
  }

  
}
