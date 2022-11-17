import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { appRoutes } from './app-routing.module';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UpdateOrderComponent } from './update-order/update-order.component';
import { ViewOrderComponent } from './view-order/view-order.component';
import { OrderListComponent } from './order-list/order-list.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { OrderComponent } from './order/order.component';
import { AddorderComponent } from './addorder/addorder.component';
import { FooterComponent } from './footer/footer.component';
import { Customer } from './models/customer.entity';
import { CustomerSignInComponent } from './customer-sign-in/customer-sign-in.component';
import { RegistrationCompleteComponent } from './registration-complete/registration-complete.component';
import { CustomerComponent } from './customer/customer.component';
import { HomeComponent } from './home/home.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { HeaderComponent } from './header/header.component';

import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import { UpdateAppointmentComponent } from './update-appointment/update-appointment.component';
import { OpenAppointmentsComponent } from './open-appointments/open-appointments.component';
import { AppointmentDetailsComponent } from './appointment-details/appointment-details.component';
import { AppointmentUseridComponent } from './appointment-userid/appointment-userid.component';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { AdminSignInComponent } from './admin-sign-in/admin-sign-in.component';
import { CustomerAdminOperationsComponent } from './customer-admin-operations/customer-admin-operations.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { RemoveCustomerComponent } from './remove-customer/remove-customer.component';

import { AuthGuardService } from './sign-in/auth-guard.service';
import { AdminGuardService } from './sign-in/admin-guard';
import { ChangePasswordComponent } from './sign-in/change-password/change-password.component';

// import { PaymentAdminComponent } from './admin/paymentadmin/payment-admin.component';
// import { PaymentServiceService } from './service/payment-service.service';

//import { PaymentAdminComponent } from './admin/paymentadmin/payment-admin.component';

import { PaymentSuccessComponent } from './payment-success/payment-success.component';
import { AuthIntercepter } from './service/Auth.intercepter';

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    NavbarComponent,
    UpdateOrderComponent,
    ViewOrderComponent,
    OrderListComponent,
    OrderComponent,
    AddorderComponent,
    FooterComponent,
    CustomerComponent,
    HomeComponent,
    SignUpComponent,
    FooterComponent,
    NavbarComponent,
    HomeComponent,
    CustomerSignInComponent,
    RegistrationCompleteComponent,
    AppointmentComponent,
    AppointmentListComponent,
    UpdateAppointmentComponent,
    OpenAppointmentsComponent,
    AppointmentDetailsComponent,
    AdminSignInComponent,
    AppointmentUseridComponent,
    HeaderComponent,
    CustomerProfileComponent,
    AdminSignInComponent,
    CustomerAdminOperationsComponent,
    UpdateCustomerComponent,
    RemoveCustomerComponent,

    ChangePasswordComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    AuthGuardService,
    SignInComponent,
    AdminGuardService,
    [{ provide: HTTP_INTERCEPTORS, useClass: AuthIntercepter, multi: true }],
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
