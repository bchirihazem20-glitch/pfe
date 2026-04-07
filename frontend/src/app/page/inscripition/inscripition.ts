import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  AbstractControl,
  ReactiveFormsModule
} from '@angular/forms';
import { AuthService } from '../../service/auth/auth';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule,MatIconModule],
  templateUrl: './inscripition.html',
  styleUrl: './inscripition.css',
})

export class Inscripition implements OnInit {

  registerForm!: FormGroup;

  showPassword = false;
  showConfirmPassword = false;

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      nom: ['', [Validators.required, Validators.minLength(2)]],
      prenom: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      adresse: ['', Validators.required],
      telephone: ['', [Validators.required, Validators.pattern('^[0-9]{8,10}$')]],
      dateNaissance: ['', Validators.required]
    }, { validators: this.passwordMatchValidator });
  }

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  toggleConfirmPassword() {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  passwordMatchValidator(form: AbstractControl) {
  const password = form.get('password')?.value;
  const confirmPasswordControl = form.get('confirmPassword');

  if (!confirmPasswordControl) return;

  if (password !== confirmPasswordControl.value) {
    confirmPasswordControl.setErrors({
      ...confirmPasswordControl.errors,
      mismatch: true
    });
  } else {
    if (confirmPasswordControl.errors) {
      delete confirmPasswordControl.errors['mismatch'];
      if (Object.keys(confirmPasswordControl.errors).length === 0) {
        confirmPasswordControl.setErrors(null);
      }
    }
  }
}

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value).subscribe({
        next: () => {
          alert('Inscription réussie !');
          this.registerForm.reset();
        },
        error: () => {
          alert('Erreur lors de l’inscription !');
        }
      });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
}