'use strict';

var BaseController =require('./base.js');

class RoleController extends BaseController {
      async index() {

          var result=await this.ctx.model.Role.find({});
        
          await this.ctx.render('admin/role/index',{

            list:result
          });    
        
      }     
    
      async add() {
    
    
        await this.ctx.render('admin/role/add');
        
      } 

      async doAdd() {
    
        //  console.log(this.ctx.request.body);


        var role=new this.ctx.model.Role({

            title:this.ctx.request.body.title,

            description:this.ctx.request.body.description,
        })
        
        await role.save();   //注意

        await this.success('/admin/role','增加角色成功');


      } 


      
    
      async edit() {
    

        var id=this.ctx.query.id;

        var result=await this.ctx.model.Role.find({"_id":id});
    
        await this.ctx.render('admin/role/edit',{

          list:result[0]
        });
        
      } 

      async doEdit() {

        /*
        { _csrf: 'b6TZ302c-LE44hFJ7LW9q3aBsmWztZXEA3Vw',
        _id: '5b8cecf5ebad41239888d3e9',
        title: '网站编辑111',
        description: '网站编辑222' }
        */

        var _id=this.ctx.request.body._id;
        var title=this.ctx.request.body.title;
        var description= this.ctx.request.body.description;

        await this.ctx.model.Role.updateOne({"_id":_id},{
          title,description
        })
         await this.success('/admin/role','编辑角色成功');     

      } 
      
      async auth() {

          var role_id=this.ctx.request.query.id;


            var result=await this.ctx.model.Access.aggregate([
            
              {
                $lookup:{
                  from:'access',
                  localField:'_id',
                  foreignField:'module_id',
                  as:'items'      
                }      
            },
            {
                $match:{
                  "module_id":'0'
                }
            }
          
          ])


          await this.ctx.render('admin/role/auth',{

              list:result,
              role_id:role_id
            });

      }

      
      async doAuth() {



        /*
        
        1、删除当前角色下面的所有权限

        2、把获取的权限和角色增加到数据库
        
        */

    
        console.log(this.ctx.request.body);

        var role_id=this.ctx.request.body.role_id;

        var access_node=this.ctx.request.body.access_node;

        //1、删除当前角色下面的所有权限

        await this.ctx.model.RoleAccess.deleteMany({"role_id":role_id});



        //2、给role_access增加数据 把获取的权限和角色增加到数据库

        for(var i=0;i<access_node.length;i++){

            var roleAccessData=new this.ctx.model.RoleAccess({

              role_id:role_id,
              access_id:access_node[i]
            })

            roleAccessData.save();
        }


        await this.success('/admin/role/auth?id='+role_id,"授权成功");




      } 


      

}

module.exports = RoleController;
