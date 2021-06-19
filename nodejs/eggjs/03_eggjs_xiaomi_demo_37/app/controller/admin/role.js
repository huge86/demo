'use strict';

var BaseController =require('./base.js');

class RoleController extends BaseController {
      async index() {
        
           await this.ctx.render('admin/role/index');    
        
      } 
    
    
      async add() {
    
    
        await this.ctx.render('admin/role/add');
        
      } 
    
      async edit() {
    
    
        await this.ctx.render('admin/role/edit');
        
      } 

      
      async delete() {
    
    
        this.ctx.body='角色删除'
        
      } 
}

module.exports = RoleController;
