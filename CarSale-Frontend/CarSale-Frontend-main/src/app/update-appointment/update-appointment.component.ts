import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentModel } from '../appointmentModel/appointment-model';
import { AppointmentServiceService } from '../appointmentService/appointment-service.service';

@Component({
  selector: 'app-update-appointment',
  templateUrl: './update-appointment.component.html',
  styleUrls: ['./update-appointment.component.css']
})
export class UpdateAppointmentComponent implements OnInit {
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
    private router:ActivatedRoute,private route:Router) { }

  
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
  

  id!: number;
  ngOnInit(): void {
    this.id=this.router.snapshot.params['id'];
    this.appointmentServiceService.getAppointmentById(this.id).subscribe(data=> {
      this.appointmentmodel=data;
    }, error => console.log(error)
    );
  }

  goToAppointmentList(){
    
  }
  onSubmit(){
    this.appointmentmodel.customer={"role":"customer",
    "password":"test123",
    "name":"Suraj",
    "email":"naiksaurjjjh@gmail.com",
    "contactNo":"9847887585",
    "dob":"2019-11-06"}
    this.appointmentServiceService.updateAppointment(this.id,this.appointmentmodel).subscribe(data=> {
      this.route.navigate(['/appointments']);
    }, error => console.log(error));
  }

}
