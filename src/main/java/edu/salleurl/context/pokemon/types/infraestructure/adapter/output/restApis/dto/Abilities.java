package edu.salleurl.context.pokemon.types.infraestructure.adapter.output.restApis.dto;

public class Abilities {

        private Ability ability;
        private boolean is_hidden;
        private int slot;

        public Abilities() {
        }

        public Ability getAbility() {
                return ability;
        }

        public void setAbility(Ability ability) {
                this.ability = ability;
        }

        public boolean isIs_hidden() {
                return is_hidden;
        }

        public void setIs_hidden(boolean is_hidden) {
                this.is_hidden = is_hidden;
        }

        public int getSlot() {
                return slot;
        }

        public void setSlot(int slot) {
                this.slot = slot;
        }
}
