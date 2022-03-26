package edu.salleurl.context.pokemon.types.infraestructure.adapter.output.restApis.dto;

import java.util.ArrayList;

public class Pokemon {

        private ArrayList<Abilities> abilities;
        private int base_experience;
        //public ArrayList<Form> forms;
        //public ArrayList<GameIndice> game_indices;
        private int height;
        //public ArrayList<HeldItem> held_items;
        private int id;
        private boolean is_default;
        private String location_area_encounters;
        //public ArrayList<Move> moves;
        private String name;
        private int order;
        private ArrayList<Object> past_types;
        //public Species species;
        private Sprites sprites;
        private ArrayList<Stat> stats;
        private ArrayList<Types> types;
        private int weight;

        public ArrayList<Abilities> getAbilities() {
                return abilities;
        }

        public void setAbilities(ArrayList<Abilities> abilities) {
                this.abilities = abilities;
        }

        public int getBase_experience() {
                return base_experience;
        }

        public void setBase_experience(int base_experience) {
                this.base_experience = base_experience;
        }

        public int getHeight() {
                return height;
        }

        public void setHeight(int height) {
                this.height = height;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public boolean isIs_default() {
                return is_default;
        }

        public void setIs_default(boolean is_default) {
                this.is_default = is_default;
        }

        public String getLocation_area_encounters() {
                return location_area_encounters;
        }

        public void setLocation_area_encounters(String location_area_encounters) {
                this.location_area_encounters = location_area_encounters;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getOrder() {
                return order;
        }

        public void setOrder(int order) {
                this.order = order;
        }

        public ArrayList<Object> getPast_types() {
                return past_types;
        }

        public void setPast_types(ArrayList<Object> past_types) {
                this.past_types = past_types;
        }

        public Sprites getSprites() {
                return sprites;
        }

        public void setSprites(Sprites sprites) {
                this.sprites = sprites;
        }

        public ArrayList<Stat> getStats() {
                return stats;
        }

        public void setStats(ArrayList<Stat> stats) {
                this.stats = stats;
        }

        public ArrayList<Types> getTypes() {
                return types;
        }

        public void setTypes(ArrayList<Types> types) {
                this.types = types;

        }
        public int getWeight() {
                return weight;
        }

        public void setWeight(int weight) {
                this.weight = weight;
        }

        public Pokemon() {
        }
}
