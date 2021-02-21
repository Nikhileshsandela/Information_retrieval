import { Component, OnInit } from '@angular/core';
import { SearchService } from './search.service';
import { SearchComponent } from './search/search.component';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  

  navLinks = [
    {path: 'search', label: 'Search'},
    //{path: 'top-searched', label: 'All topics'}
  ];

  constructor(private dataservice : SearchService){
    
  }

  ngOnInit(){
    
  }
}
