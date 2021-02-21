import { HttpClient } from '@angular/common/http';
import { Component, OnInit, QueryList } from '@angular/core';
import { results } from '../results';
import { SearchService } from '../search.service';
import { HomeComponent } from "../home/home.component"
import {HttpParams} from '@angular/common/http'
 
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent{
  
  results$: results[] = [];
  //searchQueries[] = [];
  //query = '';

  //proxyurl = "https://cors-anywhere.herokuapp.com/";
  url = "http://localhost:8983/solr/wiki/select?fl=*%20score&q=title%3Aall%0Aextract%3Aall"
  apiUrl= 'http://localhost:8983/solr/wiki/select?q=extract%3A';
  searchedQuery = '';
  //query = "";

  constructor(private http: HttpClient, private homeComponent: HomeComponent) {
    //console.log(this.apiUrl+query)
    
  }
  ngOnInit(): void {

    console.log(this.apiUrl)
    this.http.get<any>(this.url, {observe: 'body'})
    .subscribe(resp => {
      this.results$ = resp["response"]["docs"];
     // this.results$
      //console.log(this.results$);
    });
    //console.log(this.results$)
    //console.log(this.query)
   /* console.log(this._searchservice.getResults()
     .subscribe(data => this.results$ = data)) */

     //console.log(data)

  }

  searchQuery(query: string) {
    this.searchedQuery += query;
    //this.queriesList[query] = 0;
    this.apiUrl = 'http://localhost:8983/solr/wiki/select?fl=*%20score&q=title%3A'
    //console.log(query);
    this.apiUrl += query + "%0Aextract%3A" + query;
    //console.log(this.apiUrl)

    this.http.get<any>(this.apiUrl, {observe: 'body'})
    .subscribe(resp => {
      this.results$ = resp["response"]["docs"];
     // this.results$
      //console.log(this.results$);
    });

  }

  public highlight() {

  }

  public onClickButton(title:string) {
    console.log(title)
    //this.results$[]
    for (var i=0; i < this.results$.length; i++) {
      if (this.results$[i].title === title) {
          this.results$[i].score += 1;
      }
  }
    console.log(this.results$)
  }





}
