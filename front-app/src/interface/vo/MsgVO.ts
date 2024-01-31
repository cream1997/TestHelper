export default interface MsgVO {
    no: number,
    msgId: number,
    msgName: string,
    type: number,
    sendTime: string,
    receiveTime: string,
    data: any,
    visible?: boolean
}