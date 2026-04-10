import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './message.html',
  styleUrl: './message.css'
})
export class Message {

  // 👤 liste coachs
  coaches: string[] = ['Ahmed', 'Sami', 'Yasmine'];

  // 🎯 coach sélectionné
  selectedCoach: string = 'Ahmed';

  // 💬 messages
  messages: any[] = [
    {
      sender: 'Ahmed',
      content: 'Bonjour 👋',
      time: this.getCurrentTime()
    },
    {
      sender: 'Moi',
      content: 'Salut !',
      time: this.getCurrentTime()
    }
  ];

  // ✍️ message input
  newMessage: string = '';

  // 🔄 sélectionner coach
  selectCoach(coach: string) {
    this.selectedCoach = coach;

    // (اختياري) نفرّغ messages أو تبدل حسب coach
    this.messages = [];
  }

  // 📤 envoyer message
  sendMessage() {
    if (!this.newMessage.trim()) return;

    this.messages.push({
      sender: 'Moi',
      content: this.newMessage,
      time: this.getCurrentTime()
    });

    // simulation réponse coach 🤖
    setTimeout(() => {
      this.messages.push({
        sender: this.selectedCoach,
        content: 'Réponse automatique 😉',
        time: this.getCurrentTime()
      });
    }, 1000);

    this.newMessage = '';
  }

  // 🕒 helper time
  getCurrentTime(): string {
    const now = new Date();
    return now.getHours() + ':' + now.getMinutes().toString().padStart(2, '0');
  }
}