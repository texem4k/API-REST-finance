import { Routes } from '@angular/router';
import {Index} from './Pages/Index';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: Index },
];
