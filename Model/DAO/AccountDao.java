package Model.DAO;

import Model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDao implements Dao<Account>{
    private List<Account> accounts = new ArrayList<>();

    @Override
    public Optional<Account> get(long id) {
        return Optional.ofNullable(accounts.get((int) id));
    }

    @Override
    public List<Account> getAll() {
        return accounts;
    }

    @Override
    public void save(Account account) {
        accounts.add(account);
    }

    @Override
    public void update(Account account, String[] params) {
        account.setUsername(params[0]);
        account.setPassword(params[1]);
        account.setEmail(params[2]);
        account.setAddress(params[3]);
        account.setBalance(Double.parseDouble(params[4]));
    }

    @Override
    public void delete(Account account) {
        accounts.remove(account);
    }

}
