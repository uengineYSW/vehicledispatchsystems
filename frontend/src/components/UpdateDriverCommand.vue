<template>
    <v-card outlined>
        <v-card-title>
            UpdateDriver
        </v-card-title>

        <v-card-text>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <String label="Affiliation" v-model="value.affiliation" :editMode="editMode"/>
            <String label="ContactNumber" v-model="value.contactNumber" :editMode="editMode"/>
            <Boolean label="IsAvailable" v-model="value.isAvailable" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="updateDriver"
            >
                UpdateDriver
            </v-btn>
            
            <v-btn
                    color="primary"
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
        name: 'UpdateDriverCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.name = '';
            this.value.affiliation = '';
            this.value.contactNumber = '';
            this.value.isAvailable = false;
        },
        watch: {
        },
        methods: {
            updateDriver() {
                this.$emit('updateDriver', this.value);
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

