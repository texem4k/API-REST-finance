import { Routes } from '@angular/router';
import {Index} from './Pages/Index';
import {Register} from './Pages/Register/register';
import {Login} from './Pages/Login/login';

export const routes: Routes = [
  {path: "", pathMatch: "full" , component: Index},
  {path: "Register", pathMatch: "full", component: Register},
  {path: "Login", pathMatch: "full", component: Login}
];
