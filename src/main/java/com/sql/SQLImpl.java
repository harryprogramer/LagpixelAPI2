package com.sql;

import util.Logger;

class SQLImpl implements API {
    private boolean isAuth = false;

    SQLConn sqlConn = SQLConn.getInstance();

    @Override
    public void connectToDB() {

    }

    @Override
    public void setDBUrl(String dbUrl) {
        if(!isAuth){
            return;
        }
        sqlConn.setDbUrl(dbUrl);
    }

    @Override
    public void registrSQL() {
        if(!isAuth){
            return;
        }
        sqlConn.registrSQL();
    }

    @Override
    public boolean checkDBConn() {
        if(!isAuth){
            Logger.Log_ln("Security: User Authorization is disabled", Logger.Level.WARN, Logger.Type.SYSTEM);
            return true;
        }
        if (sqlConn != null) {
            return sqlConn.checkDBconn();
        } else {
            Logger.Log_ln("An attempt was made to operate on a sql server without registration", Logger.Level.CRIT, Logger.Type.SYSTEM);
            return false;
        }
    }

    @Override
    public boolean checkPassword(String login, String password) {
        if(!isAuth){
            return true;
        }
        try {
            return sqlConn.checkPassword(login, password);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.Log_ln(e.getMessage(), Logger.Level.CRIT, Logger.Type.SYSTEM);
            return false;
        }
    }
}
