package com.example.towngame;

import android.util.Log;

import com.example.towngame.playerSelection.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class VotingSystem {
    private static TreeMap<Integer, Integer> votes = new TreeMap<>();

    public static void initialize(){
        for(int i = 0; i < GameManager.players.size(); i++){
            votes.put(GameManager.players.get(i).getId(), 0);
        }
    }

    public static void vote(Player player){
        votes.replace(player.getId(), votes.get(player.getId()) + 1);
        Log.d("VOTES", votes.toString());
    }

    public static Player getTheWinner() {
        Map.Entry<Integer, Integer> maxEntry = votes.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .findFirst()
                .orElse(null);
        Map.Entry<Integer, Integer> secondEntry = votes.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != maxEntry.getKey()) // Exclude the winner
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .findFirst()
                .orElse(null);


        assert secondEntry != null;
        if(Objects.equals(maxEntry.getValue(), secondEntry.getValue())){
            return null;
        }
        Log.d("VOTES", maxEntry.getKey().toString());
        return GameManager.players.get(maxEntry.getKey());
    }


    public static List<Map.Entry<Integer, Integer>> sortVotesByValue(HashMap<Integer, Integer> votes) {
        return votes.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
    }
}
