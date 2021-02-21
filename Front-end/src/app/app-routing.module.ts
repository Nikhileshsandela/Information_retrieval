import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';
//import { TopSearchedComponent } from './top-searched/top-searched.component';



const routes: Routes = [
  {path: 'search', component: SearchComponent},
 // {path: 'top-searched', component: TopSearchedComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [SearchComponent]