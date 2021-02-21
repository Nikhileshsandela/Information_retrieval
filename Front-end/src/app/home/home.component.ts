import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  //@Input() query: string;
  @Output() newQueryEvent = new EventEmitter<string>();

  searchNewQuery(value: string) {
    this.newQueryEvent.emit(value);
  }
  constructor() { }

  ngOnInit(): void {
  }

}