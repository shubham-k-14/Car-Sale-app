import { JsonPipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder,FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppointmentModel } from '../appointmentModel/appointment-model';
import { AppointmentServiceService } from '../appointmentService/appointment-service.service';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {

  appointmentmodel:AppointmentModel={
    "appointmentId":"",
    "location":"",
    "inspectionType":"",
    "preferredDate":"",
    "preferredTime":"",
    "customer":{
        "role":"customer",
        "password":"test123",
        "name":"Suraj",
        "email":"naiksaurjjjh@gmail.com",
        "contactNo":"9847887585",
        "dob":"2019-11-06"
    }};
  
  appointmentForm = this.fb.group({
    location:['',[Validators.required,Validators.minLength(3),Validators.pattern]],
    inspectionType:['',Validators.required],
    preferredDate:['',Validators.required],
    preferredTime:['',Validators.required],
  });

  constructor(private fb:FormBuilder,private appointmentServiceService:AppointmentServiceService,
    private router:Router) { }

  get location(){
    return this.appointmentForm.controls['location'];
  }

  get inspectionType(){
    return this.appointmentForm.controls['inspectionType'];
  }

  get preferredDate(){
    return this.appointmentForm.controls['preferredDate'];
  }
  get preferredTime(){
    return this.appointmentForm.controls['preferredTime'];
  }

  onSubmit(){
    console.log(this.appointmentmodel);
    this.addAppointment();
   }

  minDate:any=this.getDate();
  
  getDate():any{
    let date:any = new Date();  
    let toDate:any = date.getDate();
    if(toDate<10){
      toDate = '0'+ toDate;
    }
    let month:any = date.getMonth() + 1;
    if(month<10){
      month = '0'+ month;
    }
    let year=date.getFullYear();
    return year+"-"+month+"-"+toDate;
  }
  
  minTime:any="09:00";
  // @Input()
  userTime:any;
  maxTime:any="18:00"
  getTime():any{
    if(this.maxTime>this.userTime && this.userTime>this.minTime)
        return this.userTime;
    else
      alert("Please enter time within 9 to 18");    
  }

  addAppointment(){
    this.appointmentServiceService.addAppointment(this.appointmentmodel).subscribe(data =>{
      console.log(data);
      this.goToAppointmentList();
    },
    error => console.log(error));
  }

  goToAppointmentList(){
    this.router.navigate(['/appointments']);
  }
  ngOnInit(): void {
  }

}
