import { Routes } from '@angular/router';
import {Index} from './Pages/Index';
import {Login} from './Pages/Login/login';

export const routes: Routes = [
  {path: "", pathMatch: "full" , component: Index},
  {path: "Login", pathMatch: "full", component: Login}
];
