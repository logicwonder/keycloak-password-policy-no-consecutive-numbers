package ext.keycloak.policy;

import com.google.auto.service.AutoService;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.policy.PasswordPolicyProvider;
import org.keycloak.policy.PasswordPolicyProviderFactory;

@AutoService(PasswordPolicyProviderFactory.class)
public class NoConsecutiveNumbersPasswordPolicyProviderFactory implements PasswordPolicyProviderFactory {

    public static final String PROVIDER_ID = "noConsequtiveNumbers";

    @Override
	public String getDisplayName() {
		return "No Consecutive Numbers";
	}


    @Override
    public String getConfigType() {
        return null;
    }

    @Override
    public String getDefaultConfigValue() {
        return null;
    }

    @Override
	public boolean isMultiplSupported() {
		return false;
	}

    @Override
    public PasswordPolicyProvider create(KeycloakSession session) {
		
        return new NoConsecutiveNumbersPasswordPolicyProvider(session);
    }	


    @Override
	public void init(Config.Scope config) {
	}

    @Override
	public void postInit(KeycloakSessionFactory factory) {
	}


    @Override
	public void close() {
	}

	@Override
	public String getId() {
		return PROVIDER_ID;
	}


}
