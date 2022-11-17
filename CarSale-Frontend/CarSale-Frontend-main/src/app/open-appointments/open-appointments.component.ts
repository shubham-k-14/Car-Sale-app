import { Component, OnInit } from '@angular/core';
import { AppointmentModel } from '../appointmentModel/appointment-model';
import { AppointmentServiceService } from '../appointmentService/appointment-service.service';

@Component({
  selector: 'app-open-appointments',
  templateUrl: './open-appointments.component.html',
  styleUrls: ['./open-appointments.component.css']
})
export class OpenAppointmentsComponent implements OnInit {

  constructor(private appointmentServiceService:AppointmentServiceService) { }

  appointmentsModel!: AppointmentModel[];

  ngOnInit(): void {
    this.appointmentServiceService.openAppointment().subscribe(data=>{
      this.appointmentsModel=data;
    });
  }

}
