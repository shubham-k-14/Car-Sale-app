import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AdminAppComponent } from './admin-app.component';
import { adminRoutes } from './admin.routes';
import { SideBarComponent } from './sidebar/sidebar.component';
@NgModule({
    declarations:[
        AdminAppComponent,
        SideBarComponent
    ],
    imports:[
        CommonModule,
        RouterModule.forChild(adminRoutes)
    ],
    providers:[

    ]
})
export class AdminModule{

}