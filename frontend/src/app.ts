import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Index} from './app/Pages/Index';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet
  ],
  templateUrl: './app.html',
  styleUrl: './styles.css'
})
export class App {
  protected readonly title = signal('frontend');
}
