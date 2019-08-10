package br.com.lbcoutinho.mongoaggregations.migration;

import br.com.lbcoutinho.mongoaggregations.model.ApplicationUser;
import br.com.lbcoutinho.mongoaggregations.model.Promotion;
import br.com.lbcoutinho.mongoaggregations.model.PromotionApplicationUser;
import br.com.lbcoutinho.mongoaggregations.repository.ApplicationUserRepository;
import br.com.lbcoutinho.mongoaggregations.repository.PromotionApplicationUserRepository;
import br.com.lbcoutinho.mongoaggregations.repository.PromotionRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

/**
 * Migrations for database initialization.
 */
@ChangeLog(order = "001")
public class DatabaseInitMigrations {

    @ChangeSet(order = "001", id = "createApplicationUsers", author = "lbcoutinho")
    public void createApplicationUsers(ApplicationUserRepository applicationUserRepository) {
        ApplicationUser user1 = new ApplicationUser();
        user1.setName("Bob");
        user1.setDocument("50321776011");
        user1.setEmail("bob@email.com");

        ApplicationUser user2 = new ApplicationUser();
        user2.setName("Alice");
        user2.setDocument("17697647044");
        user2.setEmail("alice@email.com");

        ApplicationUser user3 = new ApplicationUser();
        user3.setName("Kevin");
        user3.setDocument("64212381060");
        user3.setEmail("kevin@email.com");

        applicationUserRepository.saveAll(Arrays.asList(user1, user2, user3));
    }

    @ChangeSet(order = "002", id = "createPromotions", author = "lbcoutinho")
    public void createPromotions(PromotionRepository promotionRepository) {
        Promotion promo1 = new Promotion();
        promo1.setTitle("Promo 1");
        promo1.setDescription("This is the promo 1 description");
        promo1.setStartDate(new Date(1547517600000L));

        Promotion promo2 = new Promotion();
        promo2.setTitle("Promo 2");
        promo2.setDescription("This is the promo 2 description");
        promo1.setStartDate(new Date(1558407600000L));

        promotionRepository.saveAll(Arrays.asList(promo1, promo2));
    }

    @ChangeSet(order = "003", id = "createPromotionApplicationUser", author = "lbcoutinho")
    public void createPromotionApplicationUser(PromotionRepository promotionRepository,
                                               PromotionApplicationUserRepository promotionApplicationUserRepository) {
        Optional<Promotion> promo1 = promotionRepository.findByTitle("Promo 1");
        Optional<Promotion> promo2 = promotionRepository.findByTitle("Promo 2");

        PromotionApplicationUser bobPromo1 = new PromotionApplicationUser("50321776011", promo1.get().getId(), true);
        PromotionApplicationUser alicePromo1 = new PromotionApplicationUser("17697647044", promo1.get().getId(), true);

        PromotionApplicationUser bobPromo2 = new PromotionApplicationUser("50321776011", promo2.get().getId(), false);
        PromotionApplicationUser kevinPromo2 = new PromotionApplicationUser("64212381060", promo2.get().getId(), true);

        promotionApplicationUserRepository.saveAll(Arrays.asList(bobPromo1, alicePromo1, bobPromo2, kevinPromo2));
    }


}
