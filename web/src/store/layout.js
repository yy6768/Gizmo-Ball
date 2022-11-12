
export default{
    state: {
        status:'layout',
        socket:null,
        gameMap:null,
        gameObject:null,
        operation:'',
    },
    getters: {

    },
    mutations: {

        updateSocket(state,socket){
            state.socket = socket;
        },
        updateStatus(state,status){
            state.status = status;
        },
        updateMap(state,gameMap){
            state.gameMap = gameMap;
        },
        updateGameObject(state, gameObject) {
            state.gameObject = gameObject;
        },
    },
    actions: {

    },
    modules: {
        
    }

}