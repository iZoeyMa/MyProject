package com.controller;

import java.util.Map;

import com.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class RewardController {

    @Autowired
    private RewardsService RewardsService;

    @GetMapping("/rewards")
    public Map<String, Integer> getAllCustomerRewardsByMonth() {
        return RewardsService.getAllCustomerRewardsByMonth();
    }

    @GetMapping("/rewards/{id}")
    public Map<Integer, Integer> getCustomerRewardsByMonth(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
              return  RewardsService.getCustomerRewardsByMonth(Id);

    }

//    @PostMapping("/rewards")
//    public RewardsModel createReward(@Valid @RequestBody RewardsModel reward) {
//        return RewardsService.createReward(reward);
//    }

//    @PutMapping("/rewards/{id}")
//    public ResponseEntity<RewardsModel> updateReward(@PathVariable(value = "id") Long rewardId,
//                                                     @Valid @RequestBody RewardsModel rewardDetails) throws ResourceNotFoundException {
//        RewardsModel reward = RewardsService.getCustomerRewardsByMonth(rewardId);
//
//        reward.setCustomer(rewardDetails.getCustomer());
//        reward.setTotalPoints(rewardDetails.getTotalPoints());
//        return ResponseEntity.ok(updatedReward);
//    }

}
