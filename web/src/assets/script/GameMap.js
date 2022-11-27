import { GimzoObject } from "./GimzoObject";
import { Ball } from './Ball'
import { Circle } from "./Circle";
import { Baffle } from "./Baffle";
import { StraightPipe } from "./StraightPipe";
import { Rectangle } from "./Rectangle";
import { Triangle } from "./Triangle";
import { Blackhole } from "./Blackhole";
import { BendPipe } from "./BendPipe";


export class GameMap extends GimzoObject {
    constructor(ctx, parent, store) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.store = store;
        this.L = 0; //块的大小
        this.rows = 20;
        this.cols = 20;

        this.ball = null;
        this.leftBaffle = null;
        this.rightBaffle = null;
        //记录每个格子放的对象id
        this.components = {};
    }

    start(){
        this.add_listening_events();
    }

    //获取当前鼠标位置
    getEventPosition(event){
        // console.log(this.L);
        let x = Math.floor(event.offsetX / this.L);
        let y = Math.floor(event.offsetY / this.L);
        return [x , y];
    }   
    

    //检查是否有物体重合
    check_position(p){
        return this.components[p] === undefined;
    }

    //设置物体已经重合
    set_position(p, id){
       this.components[p] = id;
    }
    
    /*
      在地图添加组件
      1、判别类别
      2、创建对象
      3、向后端发送消息
    */
    add(p){
        let type = this.store.state.layout.objectType;
        
        let object = null;
        // console.log(type);
        if(type === 'Ball'){
            //球体只能有一个
            if(this.ball !== null) {
                this.ball.destroy();
            }
            object = this.ball = new Ball(this, p[0], p[1], 1);
        } else if(type === 'Circle'){
            object = new Circle(this, p[0], p[1], 1);
        } else if(type === 'Blackhole'){
            object = new Blackhole(this, p[0], p[1]);
        } else if(type === 'Rectangle') {
            object = new Rectangle(this, p[0], p[1], 1);
        } else if(type === 'Triangle'){
            object = new Triangle(this, p[0], p[1], 1, 0);
        } else if(type === 'StraightPipe'){
            object = new StraightPipe(this,p[0],p[1], 0);
        } else if(type === 'BendPipe'){
            object = new BendPipe(this, p[0], p[1], 0);
        } else if(type ==='LeftBaffle'){
            if(this.leftBaffle !== null){
                this.leftBaffle.destroy();
            }
            type = "Baffle";
            object = this.leftBaffle = new Baffle(this, p[0], p[1], true);
        } else if(type ==='RightBaffle'){
            if(this.rightBaffle !== null){
                this.rightBaffle.destroy();
            }
            type = "Baffle";
            object = this.rightBaffle = new Baffle(this, p[0], p[1], false);
        } else {
            console.log("wrong type");
            return false;
        }
        this.store.commit("updateObject", object);
        this.store.state.layout.socket.send("add "+ type + " " + object.id +" "+ p[0] + " " + p[1]);
    }


    //绑定事件
    add_listening_events(){ 
        this.ctx.canvas.addEventListener("click",e =>{
            if(this.store.state.layout.status === 'layout'){ //游玩模式绑定点击事件
                let p = this.getEventPosition(e);
                let type = this.store.state.layout.objectType;
                if(type === 'LeftBaffle') {
                    p[0] = this.cols / 4 - 1;
                } else if(type === 'RightBaffle'){
                    p[0] = this.cols/ 4 * 3 - 1;
                }
                if(this.store.state.layout.objectType !== "click" && this.check_position(p)){
                    this.add(p);
                } else if(this.store.state.layout.objectType === 'click'){
                    console.log("click", p);
                    this.store.commit("updateObject", this.components[p]);
                    
                }
            } 
                
        });
        
    }

    //调整地图大小时的函数
    update_size() {
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    //更新函数
    next_step(msgs){
        const components_msgs = msgs.split(" ");
        for(let component_msg of components_msgs){
            if(component_msg === "") continue;
            const msg = component_msg.split("#");
            const x = msg[5];
            const y = msg[6];
            if(msg[0] === 'Ball'){
                this.ball.set_position(x, y);
            } else if(msg[0] === 'Baffle'){
                const isLeft = msg[4];
                console.log(isLeft);
                if(isLeft === "true") this.leftBaffle.set_position(x, y);
                else this.rightBaffle.set_position(x, y);
            }
        }
    }

    //初始化布局的函数
    init_layout(msg){
        const msgs = msg.split(" ");
        if(msgs[0] === 'endGame'){
            
            for(let i = 1; i < msgs.length; i++){
                const infos = msgs[i].split("#");
                const type = infos[0];
                const x = infos[2];
                const y = infos[3];
                if(type === 'Ball') this.ball.set_position(x, y);
                else if(type === 'Baffle'){
                    const isLeft = infos[4];
                    if(isLeft === "true") this.leftBaffle.set_position(x, y);
                    else this.rightBaffle.set_position(x, y);
                }
            }
        } else if(msgs[0] === 'initLayout'){
            this.clear();
        }
    }

    update() {
        this.update_size();
        this.render();
    }

    render() {
        const color_even = "#AAD751", color_odd = "#A2D149";
        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if ((r + c) % 2 == 0) {
                    this.ctx.fillStyle = color_even;
                } else {
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
            }
        }
    }
    //清除所有组件
    clear(){
        for(let i in this.components){
            let obj = this.components[i];
            if(obj != undefined)
                obj.destroy();
        }
    }
}