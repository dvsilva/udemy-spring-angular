import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { TemplateModule } from './template/template.module';
import { ClientesModule } from './clientes/clientes.module';

import { ClientesService } from './clientes.service';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
@NgModule({
  declarations: [AppComponent, HomeComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TemplateModule,
    ClientesModule,
    HttpClientModule,
  ],
  providers: [ClientesService],
  bootstrap: [AppComponent],
})
export class AppModule {}
