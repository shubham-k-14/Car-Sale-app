import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminSignInComponent } from './admin-sign-in/admin-sign-in.component';
import { AppComponent } from './app.component';
import { AppointmentDetailsComponent } from './appointment-details/appointment-details.component';
import { AddorderComponent } from './addorder/addorder.component';
import { CustomerSignInComponent } from './customer-sign-in/customer-sign-in.component';
import { CommonModule } from '@angular/common';
import { OrderListComponent } from './order-list/order-list.component';
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import { AppointmentUseridComponent } from './appointment-userid/appointment-userid.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { CustomerComponent } from './customer/customer.component';
import { HomeComponent } from './home/home.component';
import { OpenAppointmentsComponent } from './open-appointments/open-appointments.component';
import { RegistrationCompleteComponent } from './registration-complete/registration-complete.component';

import { UpdateOrderComponent } from './update-order/update-order.component';
import { ViewOrderComponent } from './view-order/view-order.component';

import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { CustomerAdminOperationsComponent } from './customer-admin-operations/customer-admin-operations.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { RemoveCustomerComponent } from './remove-customer/remove-customer.component';
import { AdminGuardService } from './sign-in/admin-guard';

import { AuthGuardService } from './sign-in/auth-guard.service';
import { ChangePasswordComponent } from './sign-in/change-password/change-password.component';

import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UpdateAppointmentComponent } from './update-appointment/update-appointment.component';

export const appRoutes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'sign-in', component: SignInComponent },
  {
    path: 'change-password',
    component: ChangePasswordComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'order-list',
    component: OrderListComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'addorder',
    component: AddorderComponent,
    canActivate: [AuthGuardService],
  },

  { path: 'sign-up/customer', component: CustomerComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'customer-home', component: CustomerSignInComponent },
  { path: 'registration-successful', component: RegistrationCompleteComponent },
  { path: 'addAppointment', component: AppointmentComponent },
  { path: '', redirectTo: 'appointments', pathMatch: 'full' },
  { path: 'appointments', component: AppointmentListComponent },
  { path: 'customer-profile', component: CustomerProfileComponent },

  {
    path: 'customer-admin-operations',
    component: CustomerAdminOperationsComponent,
  },
  { path: 'update-customer/:id', component: UpdateCustomerComponent },
  { path: 'delete-customer/:id', component: RemoveCustomerComponent },

  { path: 'sign-up/customer', component: CustomerComponent },
  { path: 'sign-up', component: SignUpComponent },
  {
    path: 'customer-home',
    component: CustomerSignInComponent,
    canActivate: [AuthGuardService],
  },
  { path: 'registration-successful', component: RegistrationCompleteComponent },
  {
    path: 'addAppointment',
    component: AppointmentComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'appointments',
    component: AppointmentListComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'customer-profile',
    component: CustomerProfileComponent,
    canActivate: [AuthGuardService],
  },

  {
    path: 'customer-admin-operations',
    component: CustomerAdminOperationsComponent,
  },
  {
    path: 'update-customer/:id',
    component: UpdateCustomerComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'delete-customer/:id',
    component: RemoveCustomerComponent,
    canActivate: [AuthGuardService],
  },

  {
    path: 'updateorder',
    component: UpdateOrderComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'updateorder/:id',
    component: UpdateOrderComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'vieworder',
    component: ViewOrderComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'vieworder/:id',
    component: ViewOrderComponent,
    canActivate: [AuthGuardService],
  },

  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.modules').then((m) => m.AdminModule),
    canActivateChild: [AdminGuardService],
  },

  {
    path: 'addAppointment',
    component: AppointmentComponent,
    canActivate: [AuthGuardService],
  },
  { path: '', redirectTo: 'appointments', pathMatch: 'full' },
  {
    path: 'appointments',
    component: AppointmentListComponent,
    canActivate: [AuthGuardService],
  },

  { path: 'sign-up/customer', component: CustomerComponent },
  {
    path: 'customer-home',
    component: CustomerSignInComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'registration-successful',
    component: RegistrationCompleteComponent,
    canActivate: [AuthGuardService],
  },
  { path: '', component: AdminSignInComponent, pathMatch: 'full' },
  {
    path: 'appointment-details/:id',
    component: AppointmentDetailsComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'Openappointments',
    component: OpenAppointmentsComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'updateAppointment/:id',
    component: UpdateAppointmentComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'addAppointment',
    component: AppointmentComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'appointments',
    component: AppointmentListComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'dashboard',
    component: AdminSignInComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'userAppointment',
    component: AppointmentUseridComponent,
    canActivate: [AuthGuardService],
  },
];
