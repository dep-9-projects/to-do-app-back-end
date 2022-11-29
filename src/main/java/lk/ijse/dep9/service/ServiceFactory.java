package lk.ijse.dep9.service;

import lk.ijse.dep9.service.custom.impl.ToDoItemServiceImpl;
import lk.ijse.dep9.service.custom.impl.UserServiceImpl;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    public ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return (serviceFactory == null) ? (serviceFactory = new ServiceFactory()): serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceType serviceType){
        switch (serviceType){
            case USER:
                return (T) new UserServiceImpl();
            case TO_DO_ITEM:
                return (T) new ToDoItemServiceImpl();
            default:
                return null;
        }
    }
}
