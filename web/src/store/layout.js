
export default{
    state: {
        status:'layout',
        socket:null,
        gameMap:null,
        objectType:null,
        object:undefined,
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
        updateObjectType(state, type) {
            state.objectType = type;
        },
        updateObject(state, object){
            state.object = object;
        }
    },
    actions: {

    },
    modules: {
        
    }

}