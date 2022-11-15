import { BallObject } from "./BallObject";
import { Ball } from './Ball'
import { Circle } from "./Circle";
import { Baffle } from "./Baffle";
import { StraightPipe } from "./StraightPipe";


export class GameMap extends BallObject {
    constructor(ctx, parent, store) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.store = store;
        this.L = 0; //块的大小
        this.rows = 20;
        this.cols = 20;
        //小球、左挡板、右挡板只能有一个
        this.ball = null;
        this.leftBaffle = null;
        this.rigthBaffle = null;
        //存放所有组件
        this.records = [];
        //记录每个格子放的对象id
        this.components = {};

    }

    start(){
        //初始化站位格子
        for(let i = 0; i < this.rows; i++){
            let tmp = []
            for(let j = 0; j < this.cols; j++){
                tmp.push(false);
            }
            this.records.push(tmp);
        }
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
        let x = p[0];
        let y = p[1];
        if(x >= this.rows || x < 0 || y < 0 || y >= this.cols){
            return false;
        }
        return !this.records[x][y];
    }

    //设置物体已经重合
    set_position(p, rec){
        let x = p[0];
        let y = p[1];
        this.records[x][y] = rec;
    }

    /*
      在地图添加组件
      1、判别类别
      2、判别对象是否唯一性
      3、创建对象
      4、向后端发送消息
    */
    add(p){
        let type = this.store.state.layout.gameObject;
        // console.log(type);
        let id = 0;
        if(type === 'Ball'){
            //球体只能有一个
            if(this.ball !== null) {
                this.store.state.layout.socket.send("delete "+ type + " " + this.ball.id);
                let old_p = [this.ball.c, this.ball.r];
                this.set_position(old_p,false);
                this.ball.destroy();
            }
            this.ball = new Ball(this, p[0], p[1]);
            id = this.ball.id;
        } else if(type === 'Circle'){
            let circle = new Circle(this, p[0], p[1]);
            // this.circles.push(circle);
            id = circle.id;
        } else if(type === 'Blackhole'){
            //TODO(黑洞待实现)
        } else if(type === 'Rectangle') {
            //TODO(方形待实现)
        } else if(type === 'Triangle'){
            //TODO(三角型待实现)
        } else if(type === 'StraightPipe'){
            let pipe = new StraightPipe(this,p[0],p[1]);
            id = pipe.id;
        } else if(type === 'BendPipe'){
            //TODO(弯管道待实现)
        } else if(type ==='LeftBaffle'){
            if(this.leftBaffle !== null){
                this.store.state.layout.socket.send("delete Baffle " + this.leftBaffle.id);
                let old_p = [this.leftBaffle.c,this.leftBaffle.r];
                this.set_position(old_p ,false);
                this.leftBaffle.destroy();
            }
            p[0] = this.cols / 4;
            this.leftBaffle = new Baffle(this, p[0], p[1]);
            id = this.leftBaffle.id;
        } else if(type ==='RightBaffle'){
            if(this.rigthBaffle !== null){
                this.store.state.layout.socket.send("delete Baffle " + this.rigthBaffle.id);
                let old_p = [this.leftBaffle.c,this.leftBaffle.r];
                this.set_position(old_p,false);
                this.rigthBaffle.destroy();
            }
            p[0] = this.cols/ 4 * 3 ;
            this.rigthBaffle = new Baffle(this, p[0], p[1]);
            id = this.rigthBaffle.id;
        } else if(type === 'click'){
            //TODO(选中物体)
        } else {
            console.log("wrong type");
        }
        
        this.set_position(p, true);
        this.components[p] = id;
        this.store.state.layout.socket.send("add "+ type + " " + id + " " + p[0] + " " + p[1]);
    }

    

    //绑定事件
    add_listening_events(){ 
        if(this.store.state.layout.status === 'layout'){ //游玩模式绑定点击事件
            this.ctx.canvas.addEventListener("click",e =>{
                let p = this.getEventPosition(e);
                if(this.store.state.layout.gameObject !== null && this.check_position(p)){
                    this.add(p);
                } 
            });
        } 
    }

    update_size() {
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    next_step(){
        //TODO(游戏进行时每一步变化)
    }

    update() {
        this.update_size();
        if(this.store.state.layout.status === 'playing'){
            this.next_step();
        }
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
}