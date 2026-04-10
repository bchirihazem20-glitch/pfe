import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Message {
  sender: string;
  content: string;
  time: string;
}

@Component({
  selector: 'app-messages',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './messages.html',
})
export class MessagesComponent {

  selectedCoach = 'Coach Ahmed';

  coaches = [
    'Coach Ahmed',
    'Coach Ali',
    'Coach Sami'
  ];

  newMessage = '';

  messages: Message[] = [
    { sender: 'Coach Ahmed', content: 'Bonjour, prêt pour entraînement ?', time: '10:00' },
    { sender: 'Moi', content: 'Oui coach 👍', time: '10:01' }
  ];

  sendMessage() {
    if (!this.newMessage.trim()) return;

    this.messages.push({
      sender: 'Moi',
      content: this.newMessage,
      time: new Date().toLocaleTimeString()
    });

    this.newMessage = '';

    // réponse automatique (dummy)
    setTimeout(() => {
      this.messages.push({
        sender: this.selectedCoach,
        content: 'Bien reçu 👍',
        time: new Date().toLocaleTimeString()
      });
    }, 1000);
  }

  selectCoach(coach: string) {
    this.selectedCoach = coach;
    this.messages = []; // reset chat (dummy)
  }
}