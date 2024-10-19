<template>

    <v-card outlined>
        <v-card-title>
            DeleteVehicle
        </v-card-title>

        <v-card-text>
            <String label="Model" v-model="value.model" :editMode="editMode"/>
            <String label="RegistrationNumber" v-model="value.registrationNumber" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="deleteVehicle"
            >
                DeleteVehicle
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'DeleteVehicleCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.model = '';
            this.value.registrationNumber = '';
        },
        watch: {
        },
        methods: {
            deleteVehicle() {
                this.$emit('deleteVehicle', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

