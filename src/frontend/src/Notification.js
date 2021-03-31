import {notification} from 'antd';

const openNotificationWithIcon = (type, message, description, duration, placement) => {
    placement = placement || "topRight"
    notification[type]({message, description,duration, placement});
};

export const successNotification = (message, description,placement) =>
    openNotificationWithIcon('success', message, description,10,placement);

export const infoNotification = (message, description,placement) =>
    openNotificationWithIcon('info', message, description,10,placement);

export const warningNotification = (message, description,placement) =>
    openNotificationWithIcon('warning', message, description,10,placement);

export const errorNotification = (message, description,placement) =>
    openNotificationWithIcon('error', message, description,10,placement);