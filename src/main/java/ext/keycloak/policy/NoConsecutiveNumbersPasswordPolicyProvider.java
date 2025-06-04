package ext.keycloak.policy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.policy.PasswordPolicyProvider;
import org.keycloak.policy.PolicyError;


@Slf4j
@RequiredArgsConstructor
public class NoConsecutiveNumbersPasswordPolicyProvider implements PasswordPolicyProvider {
    
    private final KeycloakSession session;


    @Override
    public PolicyError validate(RealmModel realm, UserModel user, String password) {
        Boolean enabled = (Boolean) realm.getPasswordPolicy().getPolicyConfig("noConsequtiveNumbers");

        if (enabled == null || !enabled) {
            return null;
        }
        return validate(user.getUsername(), password);
    }

    @Override
    public PolicyError validate(String user, String password) {
        if (password == null || password.length() < 2) {
            return null;
        }

        for (int i = 0; i < password.length() - 1; i++) {
            char c1 = password.charAt(i);
            char c2 = password.charAt(i + 1);

            if (Character.isDigit(c1) && Character.isDigit(c2)) {
                int n1 = c1 - '0';
                int n2 = c2 - '0';

                // Check for ascending consecutive digits
                if (n2 == n1 + 1) {
                    return new PolicyError("Password must not contain consecutive ascending numbers.");
                }
            }
        }

        return null;
    }

    
	@Override
    public Object parseConfig(String value) {
        return null;
    }

	@Override
	public void close() {
	}
}
